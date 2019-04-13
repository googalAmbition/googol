package org.ctc.googol.eventbus;

import org.greenrobot.eventbus.Subscribe;

import java.text.MessageFormat;

/**
 * @author ctc on 2019/4/13
 */
public class Subscriber {

  @Subscribe
  public void create(Message message) {
    if (Message.Type.CREATE.equals(message.getType())) {
      System.out.println(MessageFormat.format("the create  message is {0}", message));
    }
  }

  @Subscribe
  public void update(Message message) {
    if (Message.Type.UPDATE.equals(message.getType())) {
      System.out.println(MessageFormat.format("the update  message is {0}", message));
    }
  }
}
