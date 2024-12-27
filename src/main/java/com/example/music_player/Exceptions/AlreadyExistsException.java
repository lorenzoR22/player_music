package com.example.music_player.Exceptions;

public class AlreadyExistsException extends RuntimeException{
    public AlreadyExistsException(String message) {
        super(message);
    }

    public AlreadyExistsException() {
    }
}
