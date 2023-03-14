<template>
    <ul class="nav nav-pills">
        <li class="nav-item">
            <a @click="this.$emit('clearFormClick')" class="nav-link active" aria-current="page">Очистить форму</a>
        </li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" role="button"
                aria-expanded="false">Получить запрос</a>
            <ul class="dropdown-menu">
                <DropDownItem v-for="item of this.elements" @getQuery="clickHandler" :key="item" v-bind:itemText="item" />
            </ul>
        </li>
    </ul>
</template>


<script>
import DropDownItem from './DropDownItem.vue';
export default {
    name: "NavBar",
    components: { DropDownItem },
    data() {
        return {
            elements: ['SELECT ALL', 'TRUNCATE TABLE', 'DROP TABLE', 'CREATE TABLE', 'INSERT', 'FIRST SELECT', 'SECOND SELECT', 'THIRD SELECT']
        }
    },
    methods: {
        clickHandler(itemText) {
            this.$emit('getQuery', itemText)
        }
    }
}
</script>

<style scoped>
.nav-link {
    cursor: pointer;
}

@media (max-width: 767px) {
    .nav {
        flex-direction: column;
    }
}
.nav {
    display: flex;
    justify-content: center;
    flex-direction: row;
    align-items: center;
}

.nav-item.dropdown {
    margin-top: 10px;
}
</style>
