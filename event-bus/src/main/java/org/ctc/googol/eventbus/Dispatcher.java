package org.ctc.googol.eventbus;

import org.greenrobot.eventbus.EventBus;

/**
 * @author ctc on 2019/4/13
 */
public class Dispatcher {

    private EventBus eventBus = new EventBus();

    private Subscriber subscriber = new Subscriber();

    {
        eventBus.register(subscriber);
    }

    public void dispatcher(Message message) {
        eventBus.post(message);
    }
}
