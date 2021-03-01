package com.example.demo.entity;

import org.junit.jupiter.api.Test;

import static com.example.demo.DtoTestUtils.validateAccessors;
import static org.junit.jupiter.api.Assertions.*;

class UsersEntityTest {
    @Test
    public void testAccessors() {
        validateAccessors(UsersEntity.class);
    }
}
