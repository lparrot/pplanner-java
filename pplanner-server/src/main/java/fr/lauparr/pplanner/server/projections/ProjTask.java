package fr.lauparr.pplanner.server.projections;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ProjTask {

	String getId();

	String getName();

	String getDescription();

	BigDecimal getOrderIndex();

	@JsonFormat(pattern = "dd/MM/yyyy")
	LocalDateTime getCreatedAt();

	@Value("#{target.createdBy?.member?.fullname}")
	String getCreatorName();

	ProjTaskStatus getStatus();

}
