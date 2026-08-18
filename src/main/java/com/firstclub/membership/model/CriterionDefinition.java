package com.firstclub.membership.model;

import com.firstclub.membership.enums.CriterionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CriterionDefinition {

    private CriterionType type;
    private Map<String, Object> config;
}
