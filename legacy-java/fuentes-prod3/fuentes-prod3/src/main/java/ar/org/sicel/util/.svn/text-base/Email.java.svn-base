package ar.org.sicel.util;

import java.io.Serializable;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

/** This is a wrapper class encapsulating functionality in the java activation
 *  framework.  This object sets up an email, which can be plain text
 *  or HTML.
 */
public class Email implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4035584460281201744L;
	private String replyTo              = "";
    private String from                 = "";
    private Vector<String> toList               = null;
    private Vector<String> ccList               = null;
    private Vector<String> bccList              = null;
    private String subject              = "";
    private String htmlBody             = "";
    private String textBody             = "";
    //private Collection<Attachment> attachments      = null;
    private boolean htmlMode            = false;

    /** Constructor */
    public Email() {
        this.toList = new Vector<String>();
        this.ccList = new Vector<String>();
        this.bccList = new Vector<String>();
        /*this.attachments = new Vector<Attachment>(); */
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    /**
     * Set the sender
     *
     * @param from The from name
     */
    public void setFrom( String from ){
        this.from = from;
    }

    public String getFrom() {
        return this.from;
    }


    /** Add a name to the to list
     *
     * @param to The to name
     */
    public void addTo(String to) {
        if (to != null && !"".equals(to.trim()) && !toList.contains(to))
            this.toList.add(to);
    }

    public Vector<String> getToList() {
        return this.toList;
    }

    public String getTo(){
        Iterator<String> iter = getToList().iterator();
        StringBuffer list = new StringBuffer();
        while (iter.hasNext()){
            list.append(iter.next());
            if (iter.hasNext())
                list.append(", ");
        }
        return list.toString();
    }

    public void setTo(String to) {
        toList.clear();
        StringTokenizer st = new StringTokenizer(to, ",");
        while(st.hasMoreTokens()) {
            String s = st.nextToken();
            addTo(s);
        }
    }

    /** Add a name to the CC list
     *
     * @param cc The CC name
     */
    public void addCC(String cc){
        this.ccList.add(cc);
    }

    public Vector<String> getCcList() {
        return this.ccList;
    }


    /** Set the emails subject
     *
     * @param subject The subject
     */
    public void setSubject(String subject){
        this.subject = subject;
    }

    public String getSubject() {
        return this.subject;
    }


    /** Set the body content
     *
     * @param body The body
     */
    public void setBody(String body){
        this.textBody = body;
        this.htmlBody = body;
    }

    public String getBody() {
        return this.textBody;
    }

    public void setHtmlBody(String body){
        this.htmlBody = body;
    }

    public String getHtmlBody(){
        return this.htmlBody;
    }

    public void setTextBody(String body){
        this.textBody = body;
    }

    public String getTextBody(){
        return this.textBody;
    }


    /** Set whether or not this email should be send in HTML mode
     *
     * @param b T is HTML mode, F is text mode
     */
    public void setHTMLMode(boolean b){
        this.htmlMode = b;
    }

    /** Is this email in HTML mode?
     *
     * @return boolean T is HTML mode, F is text mode
     */
    public boolean isHTMLMode(){
        return this.htmlMode;
    }

   /* public boolean hasAttachements() {
        return attachments != null && attachments.size() > 0;
    }

    public Collection<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Collection<Attachment> attachments) {
        this.attachments = attachments;
    } */

    public Vector<String> getBccList() {
        return bccList;
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("\n")
                .append(" From: ").append(getFrom())
                .append(" To: ").append(getTo())
                .append(" Cc: ").append(getCcList())
                .append(" Bcc: ").append(getBccList())
                .append(" Subject: ").append(getSubject());
        return buf.toString();
    }


    /*public void attach(Attachment attachment) {
        attachments.add(attachment);
    }

    public void removeAttachment(int remoteIndex) {
        if (remoteIndex < attachments.size()) {
            attachments.remove(attachments.toArray()[remoteIndex]);
        }

    }  */

    public boolean hasRecipients() {
        return !toList.isEmpty();
    }
}