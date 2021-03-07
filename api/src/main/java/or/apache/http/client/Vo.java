package or.apache.http.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class Vo {
    private long id;
    private String name;
    private String pictureUrl;
    private String link;
    private String price;
}
