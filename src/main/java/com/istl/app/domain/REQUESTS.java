package com.istl.app.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A REQUESTS.
 */
@Entity
@Table(name = "requests")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class REQUESTS implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Size(max = 20)
    @Column(name = "m_obilenumber", length = 20)
    private String mOBILENUMBER;

    @Size(max = 50)
    @Column(name = "a_ccountno", length = 50)
    private String aCCOUNTNO;

    @Size(max = 10)
    @Column(name = "c_urrency", length = 10)
    private String cURRENCY;

    @Size(max = 50)
    @Column(name = "c_if", length = 50)
    private String cIF;

    @Size(max = 50)
    @Column(name = "r_equesttype", length = 50)
    private String rEQUESTTYPE;

    @Column(name = "r_equestcharge")
    private Double rEQUESTCHARGE;

    @Size(max = 10)
    @Column(name = "r_equeststatus", length = 10)
    private String rEQUESTSTATUS;

    @Column(name = "d_aterequested")
    private Instant dATEREQUESTED;

    @Size(max = 50)
    @Column(name = "t_rnrefno", length = 50)
    private String tRNREFNO;

    @Column(name = "n_oofbooks")
    private Long nOOFBOOKS;

    @Size(max = 100)
    @Column(name = "n_oofleaves", length = 100)
    private String nOOFLEAVES;

    @Column(name = "a_pproved")
    private Long aPPROVED;

    @Size(max = 10)
    @Column(name = "c_hannel", length = 10)
    private String cHANNEL;

    @Size(max = 50)
    @Column(name = "a_pprovedby", length = 50)
    private String aPPROVEDBY;

    @Column(name = "a_pprovedon")
    private Instant aPPROVEDON;

    @Size(max = 200)
    @Column(name = "c_heckerremarks", length = 200)
    private String cHECKERREMARKS;

    @Size(max = 20)
    @Column(name = "r_espcode", length = 20)
    private String rESPCODE;

    @Size(max = 200)
    @Column(name = "r_espdescription", length = 200)
    private String rESPDESCRIPTION;

    @Column(name = "d_ateresponded")
    private Instant dATERESPONDED;

    @Size(max = 150)
    @Column(name = "c_ustomername", length = 150)
    private String cUSTOMERNAME;

    @Column(name = "r_ejected")
    private Long rEJECTED;

    @Size(max = 50)
    @Column(name = "r_ejectedby", length = 50)
    private String rEJECTEDBY;

    @Column(name = "r_ejectedon")
    private Instant rEJECTEDON;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public REQUESTS id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getmOBILENUMBER() {
        return this.mOBILENUMBER;
    }

    public REQUESTS mOBILENUMBER(String mOBILENUMBER) {
        this.setmOBILENUMBER(mOBILENUMBER);
        return this;
    }

    public void setmOBILENUMBER(String mOBILENUMBER) {
        this.mOBILENUMBER = mOBILENUMBER;
    }

    public String getaCCOUNTNO() {
        return this.aCCOUNTNO;
    }

    public REQUESTS aCCOUNTNO(String aCCOUNTNO) {
        this.setaCCOUNTNO(aCCOUNTNO);
        return this;
    }

    public void setaCCOUNTNO(String aCCOUNTNO) {
        this.aCCOUNTNO = aCCOUNTNO;
    }

    public String getcURRENCY() {
        return this.cURRENCY;
    }

    public REQUESTS cURRENCY(String cURRENCY) {
        this.setcURRENCY(cURRENCY);
        return this;
    }

    public void setcURRENCY(String cURRENCY) {
        this.cURRENCY = cURRENCY;
    }

    public String getcIF() {
        return this.cIF;
    }

    public REQUESTS cIF(String cIF) {
        this.setcIF(cIF);
        return this;
    }

    public void setcIF(String cIF) {
        this.cIF = cIF;
    }

    public String getrEQUESTTYPE() {
        return this.rEQUESTTYPE;
    }

    public REQUESTS rEQUESTTYPE(String rEQUESTTYPE) {
        this.setrEQUESTTYPE(rEQUESTTYPE);
        return this;
    }

    public void setrEQUESTTYPE(String rEQUESTTYPE) {
        this.rEQUESTTYPE = rEQUESTTYPE;
    }

    public Double getrEQUESTCHARGE() {
        return this.rEQUESTCHARGE;
    }

    public REQUESTS rEQUESTCHARGE(Double rEQUESTCHARGE) {
        this.setrEQUESTCHARGE(rEQUESTCHARGE);
        return this;
    }

    public void setrEQUESTCHARGE(Double rEQUESTCHARGE) {
        this.rEQUESTCHARGE = rEQUESTCHARGE;
    }

    public String getrEQUESTSTATUS() {
        return this.rEQUESTSTATUS;
    }

    public REQUESTS rEQUESTSTATUS(String rEQUESTSTATUS) {
        this.setrEQUESTSTATUS(rEQUESTSTATUS);
        return this;
    }

    public void setrEQUESTSTATUS(String rEQUESTSTATUS) {
        this.rEQUESTSTATUS = rEQUESTSTATUS;
    }

    public Instant getdATEREQUESTED() {
        return this.dATEREQUESTED;
    }

    public REQUESTS dATEREQUESTED(Instant dATEREQUESTED) {
        this.setdATEREQUESTED(dATEREQUESTED);
        return this;
    }

    public void setdATEREQUESTED(Instant dATEREQUESTED) {
        this.dATEREQUESTED = dATEREQUESTED;
    }

    public String gettRNREFNO() {
        return this.tRNREFNO;
    }

    public REQUESTS tRNREFNO(String tRNREFNO) {
        this.settRNREFNO(tRNREFNO);
        return this;
    }

    public void settRNREFNO(String tRNREFNO) {
        this.tRNREFNO = tRNREFNO;
    }

    public Long getnOOFBOOKS() {
        return this.nOOFBOOKS;
    }

    public REQUESTS nOOFBOOKS(Long nOOFBOOKS) {
        this.setnOOFBOOKS(nOOFBOOKS);
        return this;
    }

    public void setnOOFBOOKS(Long nOOFBOOKS) {
        this.nOOFBOOKS = nOOFBOOKS;
    }

    public String getnOOFLEAVES() {
        return this.nOOFLEAVES;
    }

    public REQUESTS nOOFLEAVES(String nOOFLEAVES) {
        this.setnOOFLEAVES(nOOFLEAVES);
        return this;
    }

    public void setnOOFLEAVES(String nOOFLEAVES) {
        this.nOOFLEAVES = nOOFLEAVES;
    }

    public Long getaPPROVED() {
        return this.aPPROVED;
    }

    public REQUESTS aPPROVED(Long aPPROVED) {
        this.setaPPROVED(aPPROVED);
        return this;
    }

    public void setaPPROVED(Long aPPROVED) {
        this.aPPROVED = aPPROVED;
    }

    public String getcHANNEL() {
        return this.cHANNEL;
    }

    public REQUESTS cHANNEL(String cHANNEL) {
        this.setcHANNEL(cHANNEL);
        return this;
    }

    public void setcHANNEL(String cHANNEL) {
        this.cHANNEL = cHANNEL;
    }

    public String getaPPROVEDBY() {
        return this.aPPROVEDBY;
    }

    public REQUESTS aPPROVEDBY(String aPPROVEDBY) {
        this.setaPPROVEDBY(aPPROVEDBY);
        return this;
    }

    public void setaPPROVEDBY(String aPPROVEDBY) {
        this.aPPROVEDBY = aPPROVEDBY;
    }

    public Instant getaPPROVEDON() {
        return this.aPPROVEDON;
    }

    public REQUESTS aPPROVEDON(Instant aPPROVEDON) {
        this.setaPPROVEDON(aPPROVEDON);
        return this;
    }

    public void setaPPROVEDON(Instant aPPROVEDON) {
        this.aPPROVEDON = aPPROVEDON;
    }

    public String getcHECKERREMARKS() {
        return this.cHECKERREMARKS;
    }

    public REQUESTS cHECKERREMARKS(String cHECKERREMARKS) {
        this.setcHECKERREMARKS(cHECKERREMARKS);
        return this;
    }

    public void setcHECKERREMARKS(String cHECKERREMARKS) {
        this.cHECKERREMARKS = cHECKERREMARKS;
    }

    public String getrESPCODE() {
        return this.rESPCODE;
    }

    public REQUESTS rESPCODE(String rESPCODE) {
        this.setrESPCODE(rESPCODE);
        return this;
    }

    public void setrESPCODE(String rESPCODE) {
        this.rESPCODE = rESPCODE;
    }

    public String getrESPDESCRIPTION() {
        return this.rESPDESCRIPTION;
    }

    public REQUESTS rESPDESCRIPTION(String rESPDESCRIPTION) {
        this.setrESPDESCRIPTION(rESPDESCRIPTION);
        return this;
    }

    public void setrESPDESCRIPTION(String rESPDESCRIPTION) {
        this.rESPDESCRIPTION = rESPDESCRIPTION;
    }

    public Instant getdATERESPONDED() {
        return this.dATERESPONDED;
    }

    public REQUESTS dATERESPONDED(Instant dATERESPONDED) {
        this.setdATERESPONDED(dATERESPONDED);
        return this;
    }

    public void setdATERESPONDED(Instant dATERESPONDED) {
        this.dATERESPONDED = dATERESPONDED;
    }

    public String getcUSTOMERNAME() {
        return this.cUSTOMERNAME;
    }

    public REQUESTS cUSTOMERNAME(String cUSTOMERNAME) {
        this.setcUSTOMERNAME(cUSTOMERNAME);
        return this;
    }

    public void setcUSTOMERNAME(String cUSTOMERNAME) {
        this.cUSTOMERNAME = cUSTOMERNAME;
    }

    public Long getrEJECTED() {
        return this.rEJECTED;
    }

    public REQUESTS rEJECTED(Long rEJECTED) {
        this.setrEJECTED(rEJECTED);
        return this;
    }

    public void setrEJECTED(Long rEJECTED) {
        this.rEJECTED = rEJECTED;
    }

    public String getrEJECTEDBY() {
        return this.rEJECTEDBY;
    }

    public REQUESTS rEJECTEDBY(String rEJECTEDBY) {
        this.setrEJECTEDBY(rEJECTEDBY);
        return this;
    }

    public void setrEJECTEDBY(String rEJECTEDBY) {
        this.rEJECTEDBY = rEJECTEDBY;
    }

    public Instant getrEJECTEDON() {
        return this.rEJECTEDON;
    }

    public REQUESTS rEJECTEDON(Instant rEJECTEDON) {
        this.setrEJECTEDON(rEJECTEDON);
        return this;
    }

    public void setrEJECTEDON(Instant rEJECTEDON) {
        this.rEJECTEDON = rEJECTEDON;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof REQUESTS)) {
            return false;
        }
        return getId() != null && getId().equals(((REQUESTS) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "REQUESTS{" +
            "id=" + getId() +
            ", mOBILENUMBER='" + getmOBILENUMBER() + "'" +
            ", aCCOUNTNO='" + getaCCOUNTNO() + "'" +
            ", cURRENCY='" + getcURRENCY() + "'" +
            ", cIF='" + getcIF() + "'" +
            ", rEQUESTTYPE='" + getrEQUESTTYPE() + "'" +
            ", rEQUESTCHARGE=" + getrEQUESTCHARGE() +
            ", rEQUESTSTATUS='" + getrEQUESTSTATUS() + "'" +
            ", dATEREQUESTED='" + getdATEREQUESTED() + "'" +
            ", tRNREFNO='" + gettRNREFNO() + "'" +
            ", nOOFBOOKS=" + getnOOFBOOKS() +
            ", nOOFLEAVES='" + getnOOFLEAVES() + "'" +
            ", aPPROVED=" + getaPPROVED() +
            ", cHANNEL='" + getcHANNEL() + "'" +
            ", aPPROVEDBY='" + getaPPROVEDBY() + "'" +
            ", aPPROVEDON='" + getaPPROVEDON() + "'" +
            ", cHECKERREMARKS='" + getcHECKERREMARKS() + "'" +
            ", rESPCODE='" + getrESPCODE() + "'" +
            ", rESPDESCRIPTION='" + getrESPDESCRIPTION() + "'" +
            ", dATERESPONDED='" + getdATERESPONDED() + "'" +
            ", cUSTOMERNAME='" + getcUSTOMERNAME() + "'" +
            ", rEJECTED=" + getrEJECTED() +
            ", rEJECTEDBY='" + getrEJECTEDBY() + "'" +
            ", rEJECTEDON='" + getrEJECTEDON() + "'" +
            "}";
    }
}
