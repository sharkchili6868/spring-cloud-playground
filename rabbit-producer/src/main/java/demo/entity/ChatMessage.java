package demo.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ChatMessage implements Serializable {

    private String name;
    private String message;

    @Override
    public String toString() {
        return "ChatMessage{" +
                "name='" + name + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
