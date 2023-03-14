class ServerConnector {
    // static SERVER_URL = "http://localhost:8080/"
    // static SERVER_URL = "http://192.168.1.6:8080/"
    static SERVER_URL = "/"


    
    getTruncateTableQuery() {
        return this.getServerSQLQuery("TRUNCATE")
    }

    getDropTableQuery() {
        return this.getServerSQLQuery("DROP")
    }

    getCreateTableQuery() {
        return this.getServerSQLQuery("CREATE")
    }

    getInsertTableQuery() {
        return this.getServerSQLQuery("INSERT")
    }

    getFirstSelectQuery() {
        return this.getServerSQLQuery("FIRST_SELECT")
    }
    
    getSecondSelectQuery() {
        return this.getServerSQLQuery("SECOND_SELECT")
    }
    
    getThirdSelectQuery() {
        return this.getServerSQLQuery("THIRD_SELECT")
    }

    getSelectAllQuery() {
        return this.getServerSQLQuery("SELECT_ALL")
    }


    getServerSQLQuery(type) {
        let headers = {
            "Content-Type": "application/json"
        }
        return fetch(`${ServerConnector.SERVER_URL}get_query?type=${type}`, {
            method: "GET",
            headers: headers
        })
    }

    executeQuery(query) {
        let body =  {
            "query": query
        };
        let headers = {
            "Content-Type": "application/json"
        }
        return fetch(`${ServerConnector.SERVER_URL}execute`, {
            method: "POST",
            body: JSON.stringify(body),
            headers: headers
        })
    }
}

export default ServerConnector