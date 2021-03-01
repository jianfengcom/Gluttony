package or.springframework.context;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class SendEmailListener implements ApplicationListener<SendEmailEvent> {

    @Async // 开启异步
    @Override
    public void onApplicationEvent(SendEmailEvent event) {
        String content = event.getContent();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(content.equals("邮件1:有内鬼")){ // 验证异步
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("--receive:" + content);
    }
}
