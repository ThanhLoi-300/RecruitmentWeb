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

function deleteItem(url,id){
alert(id)
console.log(url+id)
        Swal.fire({
                title: 'Do you want to delete?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonText: 'Yes',
            }).then((result) => {
                if (result.isConfirmed){
                    //location.href = url+id
                }
            })
    }


function warning() {
        let icon = '';
        let title = '';
        let text = '';
        if (mess == "Name role is required") {
            icon = 'warning'
            title = 'Oosp. Fail!!!'
            text = 'Name role is required'
        }else if(mess == "ERROR"){
            icon = 'warning'
            title = 'Oosp. Fail!!!'
            text = "This role can not be deleted because it has been used by some account"
        }else{
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
    warning()



