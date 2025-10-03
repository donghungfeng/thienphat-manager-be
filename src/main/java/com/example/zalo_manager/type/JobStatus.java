package com.example.zalo_manager.type;

public enum JobStatus {
    PENDING(0), DONE(1);

    private final int code;
    JobStatus(int code){ this.code = code; }
    public int getCode(){ return code; }

    public static JobStatus fromCode(int code){
        for(JobStatus s : values()) if(s.code==code) return s;
        throw new IllegalArgumentException("Invalid JobStatus code: " + code);
    }
}