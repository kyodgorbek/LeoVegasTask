package activity.drawer.navigation.com.leovegastask.Pojo;

public class HeroesResponse
{
    private String code;
    private String status;
    private String etag;
    private Data data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HeroesResponse{" +
                "code='" + code + '\'' +
                ", status='" + status + '\'' +
                ", etag='" + etag + '\'' +
                ", data=" + data +
                '}';
    }
}