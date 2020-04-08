/**
 * Copyright (C) 2013, Xiaomi Inc. All rights reserved.
 */

package org.ctc.googol.eventbus;

import lombok.Data;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.junit.Test;

public class EventBusTest {

    @Test
    public void test1(){
        EventBus.getDefault().register(new subscribe());
        Event event = new Event();
        event.setMessage("hello world");
        EventBus.getDefault().post(event);
    }

    @Data
    public static class Event{
        private String message;

    }

    public class subscribe {

        @Subscribe
        public void process(Event event){
            System.out.println(event);
        }

    }



}
