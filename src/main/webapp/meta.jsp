<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<!-- Website Title, Description and Icon -->
<title>General List Manager</title>
<meta name="description" content="UCL COMP0004-Object-Orientated Programming Coursework 3">
<link rel="icon" href="https://image.flaticon.com/icons/png/512/12/12736.png">

<!-- Google Fonts -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i">

<!-- Vendor CSS Files -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">

<!-- Main CSS File -->
<link rel="stylesheet" href="assets/css/style.css">

<script>
    <%-- Filters Table with searchKey --%>
    function filterTable(startColumnIndex, endColumnIndex) {
        // Declare variables
        let input, filter, table, tr, td, i, j, txtValue;
        input = document.getElementById("searchKey");
        filter = input.value.toUpperCase();
        table = document.getElementById("dataTable");
        tr = table.getElementsByTagName("tr");

        // Loop through all table rows and through each table data, and hide those who don't match the search query
        for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td");
            for (j = startColumnIndex; j < endColumnIndex; j++) {
                tr[0].style.display = "";
                if (td[j]) {
                    txtValue = td[j].textContent || td[j].innerText;
                    if (txtValue.toUpperCase().indexOf(filter) > -1) {
                        tr[i].style.display = "";
                        break;
                    } else {
                        tr[i].style.display = "none";
                    }
                }
            }
        }
    }

</script>

<!-- Vendor JS Files -->
<script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>