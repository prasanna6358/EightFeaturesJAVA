package pssh.com.mule;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class App 
{
    public static void main( String[] args ) {

        try{
            JSch jsch=new JSch();
            Session session=jsch.getSession("Administrator", "52.53.232.50", 22);
            session.setPassword("mulesoft@12345");
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            Channel channel = session.getStreamForwarder("52.53.232.50", 22);
            channel = session.openChannel("exec");
            channel.setInputStream(System.in);
            channel.setOutputStream(System.out);
            channel.connect(1000);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
