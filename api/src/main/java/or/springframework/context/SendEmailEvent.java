package or.springframework.context;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

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
