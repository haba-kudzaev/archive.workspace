
# Summary

This is a first past attempt at converting the new Bootstrap 4 Grid system (mixins) from SCSS to LESS.

I tend to have need for both languages for different projects so having the same functionality in both places seemed like a good idea.

The super base functionality has been roughly converted, but there's some mixins and whatnot that are just commented out for now.

Use as your own risk.

This bears the same licensing as Bootstrap itself since that's where it came from.

## Usage

Just include the bootstrap-grid.less file in wherever you prefer and then you can do the following:

Make a container:

    section {

      .make-container();

    }

Make a row:

    section {

      .make-row();

    }

Make a column:

    section {

      .make-col();

    }

Make an elment span so far:

    section {

      .make-col();
      .make-col-span(12);

    }

Use breakpoints for things:

    section {

      .media-breakpoint-up(@lg, {
          max-width: 60em;
      });

      .media-breakpoint-up(@xl, {
          max-width: 73.125em;
      });

    }
