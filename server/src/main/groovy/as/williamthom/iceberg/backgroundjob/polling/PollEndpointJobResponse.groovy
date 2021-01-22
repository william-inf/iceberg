package as.williamthom.iceberg.backgroundjob.polling

class PollEndpointJobResponse {
    boolean success

    static PollEndpointJobResponse success() {
        return new PollEndpointJobResponse(success: true)
    }

    static PollEndpointJobResponse failure() {
        return new PollEndpointJobResponse(success: false)
    }
}