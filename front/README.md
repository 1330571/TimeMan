# front

## Project setup

```
yarn install
```

### Compiles and hot-reloads for development

```
yarn serve
```

### Compiles and minifies for production

```
yarn build
```

### Lints and fixes files

```
yarn lint
```

### Customize configuration

See [Configuration Reference](https://cli.vuejs.org/config/).

## Project purpose

It's almost a template to show some views and layouts based on vuetify framework. while this project is only write for
homework, so the codes are poor-organized, and this front end make cros Http Request to localhost:8081/, so just look
this front end as a toy.

## Something about DataBase

- for task
    - state
        - 0 -> ready to go
        - 1 -> ongoing
        - 2 -> complete
        - 3 -> overdue
        - 4 -> removed
        - these all four statuses are linked with your task, and render in different colors in the front end.
    - states_list
        - this attribute is a string combines from each user's task status, if this task is individual, only one element
          of this list, so task type is implicit.
