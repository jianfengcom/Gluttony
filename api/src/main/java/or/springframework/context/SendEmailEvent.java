package or.springframework.context;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @Description:
 * @Author
 * @Date 2020/12/24
 * @Version 1.0
 */

@Getter
public class SendEmailEvent extends ApplicationEvent {
    private String content;

    public SendEmailEvent(Object source) {
        super(source);
    }
    public SendEmailEvent(Object source, String content) {
        super(source);
        this.content = content;
    }
}
