import com.sun.net.httpserver.HttpExchange;
import server.BasicServer;
import server.Utils;
import service.Candidate;
import service.CandidateService;
import service.CandidatesDataModel;
import service.FileService;

import java.io.IOException;
import java.util.Map;

public class VoteMachine extends BasicServer {
    private CandidateService service ;
    public VoteMachine(String host, int port) throws IOException {
        super(host,port);
        service= new CandidateService();
        registerGet("/", this::mainHandler);
        registerPost("/vote",this::voteHandler);
        registerGet("/takevote",this::takeVoteHandler);

    }

    private void takeVoteHandler(HttpExchange exchange) {
        String raw = getQueryParams(exchange);
        Map<String,String > params = Utils.parseUrlEncoded(raw,"&");
        int id = Integer.parseInt(params.get("id"));
        Candidate candidate = service.getCandidate(id);
        candidate.setVotes(candidate.getVotes()+1);

        redirect303(exchange,"thankyou.html");
    }

    private void voteHandler(HttpExchange exchange) {
        String raw = getBody(exchange);
        Map<String,String > parsed = Utils.parseUrlEncoded(raw,"&");
        int id = Integer.parseInt(parsed.get("candidateId"));

        Candidate candidate = service.getCandidate(id);
        renderTemplate(exchange,"votes.html",getSingleCandidate(candidate));

    }

    private SingleCandidateDataModel getSingleCandidate(Candidate candidate) {
        return new SingleCandidateDataModel(candidate);
    }

    private void mainHandler(HttpExchange exchange) {
        renderTemplate(exchange,"candidates.html",getModel());
    }
    private CandidatesDataModel getModel(){
        return new CandidatesDataModel(service.getAllCandidates());
    }
}
