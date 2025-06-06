let left_eye_x ;
let left_pupil_x;
let left_pupil_y;
let right_pupil_x;
let right_pupil_y;
let right_eye_x;
let left_pupil_dx = 0;
let left_pupil_dy = 0;
let right_pupil_dx = 0;
let right_pupil_dy = 0;
let eyes_y;
let eye_whites_radius;
let pupils_radius;
let mouth_x;
let mouth_y;
let mouth_radius;
let maximum_translation_length;
let initial_pitch;
let initial_roll;
let pitch;
let roll;

let mouth_open = false;

const canvas = document.getElementById("theCanvas");
const ctx = canvas.getContext("2d");

canvas.width = window.innerWidth;
canvas.height = window.innerHeight;

draw_face();

function draw_face() {
    draw_eye_whites();
    draw_eye_pupils();
    draw_mouth();
}

function draw_eye_whites() {
    update_eye_parameters();
    ctx.fillStyle = "white";

    ctx.beginPath();
    //x, y, radius, start angle, end angle
    ctx.arc(left_eye_x, eyes_y, eye_whites_radius, 0, 2 * Math.PI);
    ctx.fill();

    ctx.beginPath();
    ctx.arc(right_eye_x, eyes_y, eye_whites_radius, 0, 2 * Math.PI);
    ctx.fill();
}

function update_eye_parameters() {
    left_eye_x = window.innerWidth / 3;
    right_eye_x = (window.innerWidth / 3) * 2;
    eyes_y = window.innerHeight / 3;
    eye_whites_radius = window.innerWidth / 15;
    pupils_radius = window.innerWidth / 40;
}

function draw_eye_pupils() {
    ctx.fillStyle = "black";

    ctx.beginPath();
    //x, y, radius, start angle, end angle
    left_pupil_x = left_eye_x + left_pupil_dx;
    left_pupil_y = eyes_y + left_pupil_dy;
    ctx.arc(left_pupil_x, left_pupil_y, pupils_radius, 0, 2 * Math.PI);
    ctx.fill();

    right_pupil_x = right_eye_x + right_pupil_dx;
    right_pupil_y = eyes_y + right_pupil_dy;
    ctx.beginPath();
    ctx.arc(right_pupil_x, right_pupil_y, pupils_radius, 0, 2 * Math.PI);
    ctx.fill();
}

function draw_mouth() {
    update_mouth_parameters();
    ctx.fillStyle = "black";
    ctx.lineWidth = 10;
    ctx.beginPath();
    if (mouth_open) {
        ctx.arc(mouth_x,mouth_y,mouth_radius * 2/3, 0, 2*Math.PI);
    } else {
        ctx.arc(mouth_x,mouth_y,mouth_radius, 0, Math.PI);
    }
    ctx.stroke();
}

function update_mouth_parameters() {
    mouth_x = window.innerWidth / 2;
    mouth_y = window.innerHeight / 2;
    mouth_radius = window.innerWidth / 25;
}

window.addEventListener('resize', () => {
    clear_everything();
    left_pupil_dx = 0;
    left_pupil_dy = 0;
    right_pupil_dx = 0;
    right_pupil_dy = 0;
    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;
    draw_face();
})


window.addEventListener('touchstart', () => {
    open_mouth();
})

window.addEventListener('touchend', () => {
    close_mouth();
})

function close_mouth() {
    mouth_open = false;
    clear_everything();
    draw_face();
}

function open_mouth() {
    mouth_open = true;
    clear_everything();
    draw_face();
}

if (window.innerWidth > window.innerHeight) {
    window.addEventListener('mouseup', () => {
        close_mouth();
    })
    window.addEventListener('mousedown', () => {
        open_mouth();
    })
}

window.addEventListener('deviceorientation', (event) => {
    if (!initial_pitch) {
        initial_pitch = event.beta;
    }
    if (!initial_roll) {
        initial_roll = event.gamma;
    }
    pitch = event.beta;
    roll = event.gamma;
    draw_eye_pupils_follow_mouse(((window.innerWidth / 10) * (roll - initial_roll)), (window.innerHeight/10) * (pitch - initial_pitch));
})

function clear_everything() {
    ctx.clearRect(0,0,canvas.width,canvas.height);
}

window.addEventListener('mousemove', (event) => {
    draw_eye_pupils_follow_mouse(event.clientX, event.clientY);
})

function draw_eye_pupils_follow_mouse(mouse_x, mouse_y) {
    update_eye_parameters();
    draw_eye_whites();
    ctx.fillStyle = "black";

    const maximum_translation_length = eye_whites_radius - pupils_radius - 5;

    const y = mouse_y - eyes_y;

    const right_x = mouse_x - right_eye_x;
    const right_eye_hypotenuse_length = Math.sqrt(right_x ** 2 + y ** 2);
    const right_scale_factor = Math.min(right_eye_hypotenuse_length, maximum_translation_length) / right_eye_hypotenuse_length;
    right_pupil_dx = right_x * right_scale_factor;
    right_pupil_dy = y * right_scale_factor;

    const left_x = mouse_x - left_eye_x;
    const left_eye_hypotenuse_length = Math.sqrt(left_x ** 2 + y ** 2);
    const left_scale_factor = Math.min(left_eye_hypotenuse_length, maximum_translation_length) / left_eye_hypotenuse_length;
    left_pupil_dx = left_x * left_scale_factor;
    left_pupil_dy = y * left_scale_factor;

    draw_eye_pupils();
}
