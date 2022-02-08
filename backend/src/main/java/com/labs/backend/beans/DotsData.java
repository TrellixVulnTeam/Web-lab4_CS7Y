package com.labs.backend.beans;

import com.labs.backend.entities.Dot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Stateless
public class DotsData {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @EJB
    private DatabaseManager dbManager;
    private Dot dot;

    public Response addDot(String login, Double x, Double y, Double r) {
        long startTime = System.nanoTime();
        String curTime = dateFormat.format(new Date());
        boolean result = checkResult(x, y, r);
        double execTime = (double) (System.nanoTime() - startTime) / 1000000;

        dot = new Dot(login, x, y, r, curTime, execTime, result);
        if (dbManager.addDot(dot)) {
            return Response
                    .status(Response.Status.OK)
                    .entity(dot)
                    .build();
        }
        return Response
                .status(Response.Status.BAD_REQUEST)
                .build();

    }

    public Response getDots() {
        return Response
                .status(Response.Status.OK)
                .entity(dbManager.getDotList())
                .build();
    }

    public Response clear(String login) {
        Response.Status status;
        if (dbManager.clearDotList(login)) {
            status = Response.Status.OK;
        }
        else {
            status = Response.Status.BAD_REQUEST;
        }
        return Response
                .status(status)
                .build();
    }

    private boolean checkResult(double x, double y, double r) {
        return checkTriangle(x, y, r) || checkRectangle(x, y, r) || checkCircle(x, y, r);
    }

    private boolean checkTriangle(double x, double y, double r){
        r = Math.abs(r);
        return x >= 0 && y >= 0 && x+y <= r;
    }

    private boolean checkRectangle(double x, double y, double r){
        r = Math.abs(r);
        return x >= 0 && x <= r && y >= -r && y <= 0;
    }

    private boolean checkCircle(double x, double y, double r){
        r = Math.abs(r);
        return x <= 0 && y >= 0 && x*x + y*y <= r*r/4;
    }
}
