package fr.lauparr.pplanner.server.projections;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ProjTask {

	String getId();

	String getName();

	String getDescription();

	BigDecimal getOrderIndex();

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	LocalDateTime getCreatedAt();

	ProjTaskStatus getStatus();

}
