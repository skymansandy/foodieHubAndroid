package in.codeshuffle.foodiehub.data.network;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class ApiHeader {

    private ProtectedApiHeader mProtectedApiHeader;
    private PublicApiHeader mPublicApiHeader;

    @Inject
    public ApiHeader(PublicApiHeader publicApiHeader, ProtectedApiHeader protectedApiHeader) {
        mPublicApiHeader = publicApiHeader;
        mProtectedApiHeader = protectedApiHeader;
    }

    public ProtectedApiHeader getProtectedApiHeader() {
        return mProtectedApiHeader;
    }

    public PublicApiHeader getPublicApiHeader() {
        return mPublicApiHeader;
    }

    public Map<String, String> getAllHeaders() {
        Map<String, String> allHeaders = new HashMap<>();
        allHeaders.putAll(getPublicApiHeader().getHeaders());
        allHeaders.putAll(getProtectedApiHeader().getHeaders());
        return allHeaders;
    }

    public static final class PublicApiHeader {

        Map<String, String> headers;

        public PublicApiHeader(Map<String, String> headers) {
            this.headers = headers;
        }

        public Map<String, String> getHeaders() {
            return headers;
        }

        public void setHeaders(Map<String, String> headers) {
            this.headers = headers;
        }
    }

    public static final class ProtectedApiHeader {

        Map<String, String> protectedheaders;

        public ProtectedApiHeader(Map<String, String> headers) {
            this.protectedheaders = headers;
        }

        public Map<String, String> getHeaders() {
            return protectedheaders;
        }

        public void setProtectedheaders(Map<String, String> protectedheaders) {
            this.protectedheaders = protectedheaders;
        }
    }
}
