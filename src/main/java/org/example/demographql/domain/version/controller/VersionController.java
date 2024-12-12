package org.example.demographql.domain.version.controller;

import org.example.demographql.domain.version.entity.Version;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class VersionController {

    @QueryMapping
    public Version getVersion() {
        return Version.getVersion();
    }
}
