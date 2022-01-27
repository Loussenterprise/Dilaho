/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dilaho;

import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;
import java.util.Scanner;

/**
 *
 * @author loussin
 */
public class SendingSMS {
    public static void send(String receiver,String message){
        VonageClient client = VonageClient.builder().apiKey("ef9b7211").apiSecret("ox1KDL0oD7WFzEg2").build();
        
        TextMessage mes = new TextMessage("LOUSSENTERPRISE",receiver,message
        );

        SmsSubmissionResponse response = client.getSmsClient().submitMessage(mes);

        if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
            System.out.println("Message sent successfully.");
        } else {
            System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
        }        
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("receiver : ");String receiver=sc.nextLine();
        System.out.print("message : ");String message=sc.nextLine();
        SendingSMS.send(receiver, message);
    }
}
