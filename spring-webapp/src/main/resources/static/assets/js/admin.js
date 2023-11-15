//js nút QL đổi màu
let btnQL = document.getElementsByClassName('QLdonhang');
let table = document.getElementsByClassName('table')
//mặc định mục chờ xác nhận là mục hiển thị đầu tiên
table[0].style.display="block"
btnQL[0].style.background="#2a2b2c"
btnQL[0].style.color="white"
            for(let i=1;i<btnQL.length;i++){
                btnQL[i].style.background="none"
                btnQL[i].style.color="black"
                table[i].style.display="none"
            }

//click mục nào đổi màu mục đó và hiển thị table tuong ứng
for(let i=0;i<btnQL.length;i++){

    btnQL[i].addEventListener('click',() =>{
        for(let i=0;i<btnQL.length;i++){
            btnQL[i].style.background="none"
            btnQL[i].style.color="black"
            table[i].style.display="none"
        }
        table[i].style.display="block"
        btnQL[i].style.background="#2a2b2c"
        btnQL[i].style.color="white"
    })
}

    function deleteItem(id){
        var itemId = id.getAttribute('data-id');
        Swal.fire({
                title: 'Do you want to delete?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonText: 'Yes',
            }).then((result) => {
                if (result.isConfirmed){
                    window.location.href = '/admin/role/delete/' + itemId
                }
            })
    }


function warning(mess) {
        let icon = '';
        let title = '';
        let text = '';

        if (mess == "Name role is required") {
            icon = 'warning'
            title = 'Oosp. Fail!!!'
            text = "Name role is required"
        }else if(mess == "This role can not be deleted because it has been used by some account"){
            icon = 'warning'
            title = 'Oosp. Fail!!!'
            text = mess
        }else if(mess == "Name is exist"){
            icon = 'warning'
            title = 'Oosp. Fail!!!'
           text = mess
        }else if(mess == "success"){
            icon = 'success'
            title = 'Congratulation!!!!!'
            text = 'Success'
        }

        if (icon != '' && title != '' && text != '')
            Swal.fire({
                icon: icon,
                title: title,
                text: text
            })
}



