package com.picpaysimplificado.dtos;

import java.math.BigDecimal;

public record TransationDTO(BigDecimal value,Long senderId, Long receiverId) {
}
