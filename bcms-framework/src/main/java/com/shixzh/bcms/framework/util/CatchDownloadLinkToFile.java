package com.shixzh.bcms.framework.util;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.regex.Pattern;

/**
 * class: CatchDownloadLinkToFile <br>
 *
 * @author: ZhaoShixiang <br>
 * @date: 2018/9/11 9:59
 */
public class CatchDownloadLinkToFile {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("批量提取下载链接");
        JButton jButton = new JButton("选择文件");
        JPanel jPanel = new JPanel();
        JLabel jLabelPrefix = new JLabel("前缀：");
        JTextField jTextFieldPrefix = new JTextField("ftp", 4);
        JLabel jLabel1Suffix = new JLabel("后缀：");
        JTextField jTextFieldSuffix = new JTextField("mp4", 4);
        JTextArea jTextArea = new JTextArea(18, 50);
        JScrollPane jScrollPane = new JScrollPane(jTextArea);

        jButton.addActionListener(e -> {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setFileSelectionMode(JFileChooser.OPEN_DIALOG | JFileChooser.FILES_ONLY);
            jFileChooser.showDialog(null, "选择文件");
            File fi = jFileChooser.getSelectedFile();
            File file = fi.getAbsoluteFile();
            System.out.println("save: " + file);
            //解析文件，并在文本框输出
            try {
                String content = splitPartOfFileToString(file, jTextFieldPrefix.getText(), jTextFieldSuffix.getText());
                jTextArea.setText(content);
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        });

        //实例化布局对象
        BorderLayout borderLayout = new BorderLayout();
        jFrame.setLayout(borderLayout);
        //新建一个滚动条界面，将文本框传入
        //注意：将滚动条界面添加到组建中
        jPanel.add(jLabelPrefix);
        jPanel.add(jTextFieldPrefix);
        jPanel.add(jLabel1Suffix);
        jPanel.add(jTextFieldSuffix);
        jPanel.add(jButton, BorderLayout.NORTH);
        jPanel.add(jScrollPane);
        jFrame.add(jPanel, BorderLayout.CENTER);
        jFrame.setSize(600, 400);
        jFrame.setVisible(true);

    }

    /**
     * 提取文件内容为一个String
     *
     * @method: splitPartOfFileToString <br>
     * @version 1.0.0 <br>
     * @author: ZhaoShixiang <br>
     * @date: 2018/9/11 10:40
     * @param file
     * @param lineHead
     * @param lineTail
     * @return java.lang.String
     */
    public static String splitPartOfFileToString(File file, String lineHead, String lineTail) {
        StringBuilder sb = new StringBuilder();
        try {
            FileInputStream in = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            final String prefixHref = "href=\"";
            final String pattern = ".*" + prefixHref + "" + lineHead + ".*";
            System.out.println("pattern:" + pattern);
            while ((line = br.readLine()) != null) {
                int startIndex = line.lastIndexOf(prefixHref + "" + lineHead) + prefixHref.length();
                int endIndex = line.indexOf("." + lineTail + "\"") + lineTail.length() + 1;
                if (Pattern.matches(pattern, line)) {
                    if (line.length() >= endIndex) {
                        sb.append(line, startIndex, endIndex);
                        sb.append("\r\n");
                    }
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 提取文件内容到另一个文件中
     *
     * @method: splitPartOfFile <br>
     * @version 1.0.0 <br>
     * @author: ZhaoShixiang <br>
     * @date: 2018/9/11 10:39
     * @param file
     * @return void
     */
    public static void splitPartOfFile(File file) {
        File result = new File("D://result_0910.txt");
        try {
            FileInputStream in = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            FileOutputStream out = new FileOutputStream(result);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
            String line;
            while ((line = br.readLine()) != null) {
                String pattern = ".*href=\"ftp.*";
                int startIndex = line.lastIndexOf("href=\"ftp");
                int endIndex = line.indexOf(".mp4\"");
                if (Pattern.matches(pattern, line)) {
                    bw.write(line.substring(startIndex + 6, endIndex + 4));
                    bw.write("\r\n");
                }
            }
            br.close();
            bw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}