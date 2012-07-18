package vigionline.vce.stream.virtual;

public class Messages {

	public static class Message {
	}

	public static class FrameMessage extends Message {
		public byte[] frame;
	}

	public static class TerminateMessage extends Message {
	}
}
