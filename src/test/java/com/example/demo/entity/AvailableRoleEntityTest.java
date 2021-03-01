package com.example.demo.entity;

import org.junit.jupiter.api.Test;

import static com.example.demo.DtoTestUtils.validateAccessors;
import static org.junit.jupiter.api.Assertions.*;

class AvailableRoleEntityTest {
    @Test
    public void testAccessors() {
        validateAccessors(AvailableRoleEntity.class);
    }
}
