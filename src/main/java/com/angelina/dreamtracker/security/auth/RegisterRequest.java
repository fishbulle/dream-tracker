package com.angelina.dreamtracker.security.auth;

public record RegisterRequest(String nickname,
                              String email,
                              String password) {}
