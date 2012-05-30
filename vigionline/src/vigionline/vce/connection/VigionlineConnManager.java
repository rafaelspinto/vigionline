package vigionline.vce.connection;

import org.apache.http.Header;
import org.apache.http.HttpResponseFactory;
import org.apache.http.ParseException;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.conn.DefaultClientConnection;
import org.apache.http.impl.conn.DefaultClientConnectionOperator;
import org.apache.http.impl.conn.DefaultResponseParser;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.io.HttpMessageParser;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicLineParser;
import org.apache.http.params.HttpParams;
import org.apache.http.util.CharArrayBuffer;

@SuppressWarnings("deprecation")
public class VigionlineConnManager extends SingleClientConnManager {

	 public VigionlineConnManager(
	            final HttpParams params,
	            final SchemeRegistry sr) {
	        super(params, sr);
	    }
	
	@Override
	protected ClientConnectionOperator createConnectionOperator(
			final SchemeRegistry sr) {
		return new MyClientConnectionOperator(sr);
	}

	class MyClientConnectionOperator extends DefaultClientConnectionOperator {

		public MyClientConnectionOperator(final SchemeRegistry sr) {
			super(sr);
		}

		@Override
		public OperatedClientConnection createConnection() {
			return new MyClientConnection();
		}

	}

	class MyClientConnection extends DefaultClientConnection {

		@Override
		protected HttpMessageParser createResponseParser(
				final SessionInputBuffer buffer,
				final HttpResponseFactory responseFactory,
				final HttpParams params) {
			return new DefaultResponseParser(buffer, new MyLineParser(),
					responseFactory, params);
		}

	}

	class MyLineParser extends BasicLineParser {

		@Override
		public Header parseHeader(final CharArrayBuffer buffer)
				throws ParseException {
			try {
				return super.parseHeader(buffer);
			} catch (ParseException ex) {
				// Suppress ParseException exception
				return new BasicHeader("invalid", buffer.toString());
			}
		}
	}
}
