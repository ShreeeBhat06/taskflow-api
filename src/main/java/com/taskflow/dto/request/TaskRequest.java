package com.taskflow.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class TaskRequest {

	 @NotBlank(message = "Title cannot be empty")
	    private String title;

	    @NotBlank(message = "Status is required")
	    @Pattern(regexp = "TODO|IN_PROGRESS|DONE", message = "Status must be TODO, IN_PROGRESS, or DONE")
	    private String status;

	    @NotBlank(message = "Priority is required")
	    @Pattern(regexp = "LOW|MEDIUM|HIGH", message = "Priority must be LOW, MEDIUM, or HIGH")
	    private String priority;
	}

