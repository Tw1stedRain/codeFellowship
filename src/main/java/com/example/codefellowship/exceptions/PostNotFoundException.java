package com.example.codefellowship.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Post not Found")
public class PostNotFoundException extends RuntimeException{
}
