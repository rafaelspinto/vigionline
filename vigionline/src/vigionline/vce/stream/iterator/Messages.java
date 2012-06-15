package vigionline.vce.stream.iterator;

public class Messages {

	public static class Message {
	}

	public static class ImageMessage extends Message {
		public byte[] image;
	}

	public static class PoisonMessage extends Message {
	}
}
