package org.qcri.xdb.api.rest.controller;

import org.qcri.xdb.api.rest.executor.RheemStudioExecutor;
import org.qcri.xdb.api.rest.model.rheemstudio.RSQuery;
import org.qcri.xdb.api.rest.model.rheemstudio.RSResponse;
import org.qcri.xdb.core.executor.XdbExecutor;
import org.qcri.xdb.core.handler.XdbHandler;
import org.qcri.xdb.core.query.XdbQuery;
import org.qcri.xdb.parser.rheemlatin.query.RheemLatinStringQuery;
import org.qcri.xdb.translate.json.rheemstudio.model.Schema;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/xdb/rs")
public class RheemStudioController {

    private XdbExecutor executor;
    {
        this.executor = RheemStudioExecutor.create();
    }

    @RequestMapping(value = "/rheemLatin2Json", method = RequestMethod.POST)
    public RSResponse rheemLatin2Json(@RequestBody RSQuery query ){
        XdbQuery xdbQuery = new RheemLatinStringQuery(query.getRheemlatinQuery());
        Object json = this.executor.execute(xdbQuery);
        RSResponse response = new RSResponse()
                .setName(query.getName())
                .setDate()
                .setJson((Schema) json);
        return response;
    }
}
