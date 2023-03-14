<template>
    <div class="base-container">
        <div class="header-container">
            <NavBar @clearFormClick="enableTextArea" @getQuery="getQuery" />
        </div>
        <div class="center-container">
            <MainTextArea v-on:dblClick="doubleClickHandle" v-bind:enabledFlag="textAreaEnabledFlag" v-model="areaText" />
        </div>
        <div class="footer-container">
            <UnderButton v-on:buttonClick="underButtonClick" v-bind:enabledFlag="buttonEnabledFlag" />
        </div>
    </div>
</template>

<script>
import NavBar from "@/components/NavigationBar/NavBar.vue"
import MainTextArea from "@/components/MainTextArea.vue"
import UnderButton from "@/components/UnderButton.vue"
import ServerConnector from "@/connection/ServerConnector"
export default {
    name: 'App',
    components: {
        NavBar,
        MainTextArea,
        UnderButton,
    },
    data() {
        return {
            textAreaEnabledFlag: true,
            buttonEnabledFlag: true,
            areaText: "",
            serverConnector: new ServerConnector(),
            lastQuery: "",
            needToShowLastQueryIfExc: false
        };
    },
    methods: {
        underButtonClick() {
            if (this.buttonEnabledFlag) {
                let query = this.areaText;
                if (query.length === 0) {
                    this.areaText = `Запрос не может быть пустым. Попробуйте еще раз`
                    this.textAreaEnabledFlag = !this.textAreaEnabledFlag;
                    this.buttonEnabledFlag = !this.buttonEnabledFlag;
                } else {
                    let resultPromise = this.serverConnector.executeQuery(this.areaText);
                    this.handleQueryResultPromose(resultPromise)
                }
            } else {
                this.areaText = ""
                this.textAreaEnabledFlag = !this.textAreaEnabledFlag;
                this.buttonEnabledFlag = !this.buttonEnabledFlag;
            }
        },

        handleQueryResultPromose(resultPromise) {
            resultPromise.then((response) => {
                if (response.status == 400) {
                    this.needToShowLastQueryIfExc = true;
                    this.lastQuery = this.areaText
                } else {
                    this.needToShowLastQueryIfExc = false;
                }
                return response.json().then((jsonBody) => {
                    this.textAreaEnabledFlag = !this.textAreaEnabledFlag;
                    this.buttonEnabledFlag = !this.buttonEnabledFlag;
                    let line = ""
                    for (let str of jsonBody.result) {
                        line += str + "\n"
                    }
                    this.areaText = line;
                });
            });
        },

        enableTextArea() {
            this.areaText = "";
            this.textAreaEnabledFlag = true;
            this.buttonEnabledFlag = true;
        },

        doubleClickHandle() {
            if (this.needToShowLastQueryIfExc) {
                this.areaText = this.lastQuery;
            } else {
                this.areaText = "";
            }
            this.textAreaEnabledFlag = true;
            this.buttonEnabledFlag = true;
        },

        getQuery(itemText) {
            let responsePromise;
            switch (itemText) {
                case "TRUNCATE TABLE":
                    responsePromise = this.serverConnector.getTruncateTableQuery();
                    this.handleResponsePromise(responsePromise);
                    break
                case "DROP TABLE":
                    responsePromise = this.serverConnector.getDropTableQuery();
                    this.handleResponsePromise(responsePromise);
                    break;
                case "CREATE TABLE":
                    responsePromise = this.serverConnector.getCreateTableQuery();
                    this.handleResponsePromise(responsePromise);
                    break;
                case "INSERT":
                    responsePromise = this.serverConnector.getInsertTableQuery();
                    this.handleResponsePromise(responsePromise);
                    break;
                case "FIRST SELECT":
                    responsePromise = this.serverConnector.getFirstSelectQuery();
                    this.handleResponsePromise(responsePromise);
                    break;
                case "SECOND SELECT":
                    responsePromise = this.serverConnector.getSecondSelectQuery();
                    this.handleResponsePromise(responsePromise);
                    break;
                case "THIRD SELECT":
                    responsePromise = this.serverConnector.getThirdSelectQuery();
                    this.handleResponsePromise(responsePromise);
                    break;
                case "SELECT ALL":
                    responsePromise = this.serverConnector.getSelectAllQuery();
                    this.handleResponsePromise(responsePromise);
                    break;
            }
        },

        handleResponsePromise(responsePromise) {
            this.needToShowLastQueryIfExc = false;
            responsePromise.then((response) => {
                return response.json().then((jsonBody) => {
                    this.enableTextArea()
                    this.areaText = jsonBody.query
                });
            });
        }


    }
}
</script>

<style>
.base-container {
    padding: 0px 30px;
    margin: 0 auto;
    max-width: 1200px;
    height: 100vh;
    display: grid;
    justify-content: center;
    grid-template-rows: 1fr 2fr 1fr;
    grid-auto-rows: 1fr;
    grid-auto-columns: 1fr;
}

.header-container {
    display: flex;
    align-items: center;
    justify-content: center;
    min-height: 100px;
}

.center-container {
    grid-row: 2/3;
    min-height: 200px;
}

.footer-container {
    grid-row: 3/4;
    min-height: 100px;
    display: flex;
    align-items: center;
    justify-content: center;
}
</style>