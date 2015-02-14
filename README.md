# hipchat-notifier
Java library that allows you send messages to HipChat rooms.
> **Note:** This library is an implementation of Sent Notification action from HipChat API v2.

##How it works
```java
 HipChatNotifier hipChatNotifier = new HipChatNotifier();
        HipChatMessage hipChatMessage = HipChatMessage.build(HipChatMessageColor.green,
                "Hi team. This message was sent using the HipChat Notifier Java library.", true,
                HipChatMessateType.text);

        try {
            if (hipChatNotifier.send("Xproject Team", "alsmdkfurekal0963hkdi2jnduysawlkdqe", hipChatMessage)) {
                System.out.println("Message sent.");
            } else {
                System.out.println("Message wasn't sent.");
            }
        } catch (UnableToSendHipChatMsgException e) {
            System.err.println(e.getLocalizedMessage());
        }
```
> **Note:** You can set the HipChat Server address using: 

```java
HipChatNotifier hipChatNotifier = new HipChatNotifier("https://hipchat.yourcompany.com");
```

## License
Copyright 2015 Rui Rodrigues
This software is under [http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0).

## Suggestions
Any suggestions are  welcome.


