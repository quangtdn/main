//@@author erik0704
package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.LocationCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new LocationCommand object
 */
public class LocationCommandParser implements Parser<LocationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the LocationCommand
     * and returns an LocationCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public LocationCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new LocationCommand(index);
        } catch (IllegalValueException ive) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, LocationCommand.MESSAGE_USAGE));
        }
    }
}
