package info.hayslip.controllers;

import info.hayslip.models.VersionID;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionDescription {

    @RequestMapping("/version")
    public VersionID reportVersion() {
        VersionID version = new VersionID();
        return version;

    }


}
