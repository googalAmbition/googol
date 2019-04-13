package org.ctc.googol.eventbus;

import org.junit.Before;
import org.junit.Test;


/**
 * @author ctc on 2019/4/13
 */
public class DispatcherTest {

  private Dispatcher dispatcher = new Dispatcher();
  private Message create;
  private Message update;

  @Before
  public void init() {
    create = Message.builder().name("hello").content("world").type(Message.Type.CREATE).build();
    update = Message.builder().name("hello").content("world").type(Message.Type.UPDATE).build();

  }

  /**
   * Output: the create  message is Message(name=hello, content=world, type=CREATE)
   */
  @Test
  public void createMessageTest() {
    dispatcher.dispatcher(create);
  }

  /**
   * Output: the update  message is Message(name=hello, content=world, type=UPDATE)
   */
  @Test
  public void updateMessageTest() {
    dispatcher.dispatcher(update);
  }
}