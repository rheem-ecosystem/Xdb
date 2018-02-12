package org.qcri.xdb.api.rest.controller;

import org.qcri.xdb.api.rest.model.rheemstudio.RSQuery;
import org.qcri.xdb.api.rest.model.rheemstudio.RSSchema;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/xdb/rs")
public class RheemStudioController {

    @RequestMapping(value = "/rheemLatin2Json", method = RequestMethod.POST)
    public RSSchema rheemLatin2Json(@RequestBody RSQuery query ){
        return new RSSchema();
    }
}
