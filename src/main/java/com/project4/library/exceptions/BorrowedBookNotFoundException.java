package com.project4.library.exceptions;

public class BorrowedBookNotFoundException extends RuntimeException{
    public BorrowedBookNotFoundException (String message){
        super(message);
    }
}
