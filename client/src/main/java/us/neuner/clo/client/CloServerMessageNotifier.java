package us.neuner.clo.client;

/*
 * Provide async notifications for when the CloApplication client 
 * receives messages from the CLO server.
 */
public interface CloServerMessageNotifier {
	public void onMessageReceived(us.neuner.clo.message.Message msg);
}
