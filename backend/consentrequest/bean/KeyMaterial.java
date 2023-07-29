package com.example.HAD.consentrequest.bean;

public class KeyMaterial {

	private String nonce;
	
	private String curve;
	
	private String cryptoAlg;
	
	private DhPublicKey dhPublicKey;

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getCurve() {
		return curve;
	}

	public void setCurve(String curve) {
		this.curve = curve;
	}

	public String getCryptoAlg() {
		return cryptoAlg;
	}

	public void setCryptoAlg(String cryptoAlg) {
		this.cryptoAlg = cryptoAlg;
	}

	public DhPublicKey getDhPublicKey() {
		return dhPublicKey;
	}

	public void setDhPublicKey(DhPublicKey dhPublicKey) {
		this.dhPublicKey = dhPublicKey;
	}
}
