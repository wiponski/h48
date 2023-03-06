package service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CandidatesDataModel {
    private List<Candidate> candidates;

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

//    public CandidatesDataModel() {
//        this.candidates =FileService.readCandidates();
//    }

    public CandidatesDataModel(List<Candidate> candidates) {
        this.candidates = candidates;
    }
}
