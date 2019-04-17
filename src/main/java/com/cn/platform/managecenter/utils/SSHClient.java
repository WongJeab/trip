package com.cn.platform.managecenter.utils;

import com.jcraft.jsch.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class SSHClient {
    private String host;
    private String username;
    private String password;
    private int port;

    private  Session session;
    private Channel channel;


    public SSHClient(String host, int port, String username, String password){
        this.host=host;
        this.username=username;
        this.password=password;
        this.port=port;
    }

    public void connect() throws JSchException {
        connect(60000);
    }

    public void connect(int connectTimeout) throws JSchException {
        JSch jsch = new JSch();
        session = jsch.getSession(username, host, port);
        session.setPassword(password);
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.connect(connectTimeout);

    }

    /**
     * 执行相关的命令
     */
    public String execCmd(String command,String charset) {
        InputStream in = null;
        BufferedReader reader = null;
        InputStream inErr = null;
        BufferedReader readerErr = null;

        try {
            channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);
            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);

            channel.connect();

            in = channel.getInputStream();
            inErr = ((ChannelExec) channel).getErrStream();

            reader = new BufferedReader(new InputStreamReader(in, Charset.forName(charset)));
            StringBuffer buf = new StringBuffer();
            String temp;
            long start=System.currentTimeMillis();
            long end =start;
            while (  end-start<=5000 && (temp = reader.readLine()) != null) {
                buf.append(temp + System.lineSeparator());
                end=System.currentTimeMillis();
            }

            readerErr = new BufferedReader(new InputStreamReader(inErr, Charset.forName(charset)));
            StringBuffer bufErr = new StringBuffer();
            String tempErr;
            while ((tempErr = readerErr.readLine()) != null) {
                bufErr.append(tempErr + System.lineSeparator());
            }

            String out = bufErr.toString().isEmpty()?buf.toString():bufErr.toString();
            return out;//.substring(0,out.lastIndexOf(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSchException e) {
            e.printStackTrace();
        } finally {
            try {
                if(reader!=null){reader.close();}
                if(in!=null){in.close();}
                if(readerErr!=null){reader.close();}
                if(inErr!=null){in.close();}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void close(){
        if(channel!=null){
            channel.disconnect();
        }
        if(session!=null){
            session.disconnect();
        }
    }

    public static void main(String[] args) throws JSchException {
        SSHClient sshClient = new SSHClient("192.168.17.137",22,"root","123456");
        sshClient.connect(10000);
        //upload(ser, servInstExtend.getPath());
        System.out.println(sshClient.execCmd("java -jar /data/webapps/test1.jar","utf-8"));  ;
        //sshClient.uploadAndRename("/data/webapps/","F:\\pub\\1.mp4","test1.jar");


    }

}
