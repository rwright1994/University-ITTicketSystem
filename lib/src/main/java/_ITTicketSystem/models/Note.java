
package _ITTicketSystem.models;

import java.time.LocalDateTime;

/**
 * Represents a note associated with a support ticket.
 * A note contains its content, author, creation date, and visibility level.
 */
public class Note {

    private final String noteId;
    private String content;
    private final User user;
    private final LocalDateTime postedDate;
    private final NoteVisibility visibility;

    /**
     * Constructs a new Note with the specified ID, content, author, and visibility.
     * The posting date is automatically set to the current date and time.
     *
     * @param noteId     unique identifier assigned to the note
     * @param content    text content of the note
     * @param user       user who created the note
     * @param visibility visibility level of the note (INTERNAL or EXTERNAL)
     */
    public Note(String noteId, String content, User user, NoteVisibility visibility) {
        this.noteId = noteId;
        this.content = content;
        this.user = user;
        this.postedDate = LocalDateTime.now();
        this.visibility = visibility;
    }

    /**
     * Returns the unique identifier of the note.
     *
     * @return the note ID
     */
    public String getNoteId() {
        return this.noteId;
    }

    /**
     * Returns the content of the note.
     *
     * @return the note content
     */
    public String getContent() {
        return content;
    }

    /**
     * Updates the content of the note.
     *
     * @param content the new content to assign to the note
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Returns the date and time when the note was created.
     *
     * @return the note's posting date and time
     */
    public LocalDateTime getPostedDate() {
        return postedDate;
    }

    /**
     * Returns the user who created the note.
     *
     * @return the note's author
     */
    public User getUser() {
        return this.user;
    }

    /**
     * Returns the visibility level assigned to the note.
     *
     * @return the note visibility (INTERNAL or EXTERNAL)
     */
    public NoteVisibility getNoteVisiblity() {
        return this.visibility;
    }

    /**
     * Returns a formatted string representation of the note,
     * including its author, posting date, and content.
     *
     * @return formatted note details
     */
    @Override
    public String toString() {
        return String.format(
            """
            --------------------------------------------------------------------------------
            Posted By:  %s
            Date:       %s
            Content:    %s
            --------------------------------------------------------------------------------
            """,
            user == null ? "Unknown" :
                user.getFirstName() + " " + user.getLastName(),
            postedDate.format(
                java.time.format.DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm")
            ),
            content
        );
    }
}
