package or.springframework.context;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author
 * @Date 2020/12/24
 * @Version 1.0
 */

@Component
public class SendEmailListener implements ApplicationListener<SendEmailEvent> {
    @Async
    @Override
    public void onApplicationEvent(SendEmailEvent event) {
        String content = event.getContent();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(content.equals("有内鬼")){
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("SendEmailListener.onApplicationEvent: content=" + content);
    }
}
