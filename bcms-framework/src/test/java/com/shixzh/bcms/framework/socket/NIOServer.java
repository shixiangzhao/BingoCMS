package com.shixzh.bcms.framework.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * 
 * @author shixzh
 *
 */
public class NIOServer {
	public static final int port = 8080;// 监听的端口号

	public static void main(String[] args) {
		System.out.println("Server...\n");
		NIOServer server = new NIOServer();
		server.init();
	}

	public void init() {
		// 创建ServerSocketChannel，监听8080端口
		try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
			serverSocketChannel.socket().bind(new InetSocketAddress(port));
			// 设置为非阻塞模式
			serverSocketChannel.configureBlocking(false);
			// 注册选择器Selector
			Selector selector = Selector.open();
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			// 创建处理器
			Handler handler = new Handler(1024);
			while (true) {
				// 等待请求，每次阻塞3s，超过3s后线程继续向下运行，如果传入0或者不传参数将一直阻塞
				if (selector.select(30000) == 0) {
					System.out.println("等待请求超时...");
					continue;
				}

				// 获取待处理的SelectionKey
				Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
				while (keyIter.hasNext()) {
					SelectionKey key = keyIter.next();
					try {
						// 接收到连接请求
						if (key.isAcceptable()) {
							handler.handleAccept(key);
						}
						// 读数据
						if (key.isReadable()) {
							handler.handleRead(key);
						}
					} catch (IOException ex) {
						keyIter.remove();
						continue;
					}
					// 处理完，从待处理的SelectionKey迭代器中移除当前所使用的key
					keyIter.remove();
				}
			}
		} catch (Exception e) {
			System.out.println("服务器异常: " + e.getMessage());
		}
	}

	private static class Handler {

		private int bufferSize = 1024;
		private String localCharset = "UTF-8";

		public Handler(int bufferSize) {
			this(bufferSize, null);
		}

		public Handler(int bufferSize, String localCharset) {
			if (bufferSize > 0) {
				this.bufferSize = bufferSize;
			}
			if (localCharset != null) {
				this.localCharset = localCharset;
			}
		}

		public void handleAccept(SelectionKey key) throws IOException {
			SocketChannel sChannel = ((ServerSocketChannel) key.channel()).accept();
			sChannel.configureBlocking(false);
			sChannel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));

		}

		public void handleRead(SelectionKey key) throws IOException {
			// 获取channel
			SocketChannel sChannel = (SocketChannel) key.channel();
			// 获取buffer并重置
			ByteBuffer buffer = (ByteBuffer) key.attachment();
			buffer.clear();
			// 没有读到内容 就关闭
			if (sChannel.read(buffer) == -1) {
				sChannel.close();
			} else {
				// 将buffer转换为读状态
				buffer.flip();
				// 将 buffer在接收到的值按localCharset格式编码后保存到receivedString
				String receivedString = Charset.forName(localCharset).newDecoder().decode(buffer).toString();
				System.out.println("received from client: " + receivedString);

				// 返回数据给客户端
				System.out.print("请输入：");
				String sendString = new BufferedReader(new InputStreamReader(System.in)).readLine();
				// String sendString = "receive data: " + receivedString;
				buffer = ByteBuffer.wrap(sendString.getBytes(localCharset));
				sChannel.write(buffer);
				// 关闭Socket
				sChannel.close();
			}
		}
	}
}
